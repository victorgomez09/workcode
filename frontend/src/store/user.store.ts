import { create } from 'zustand';
import { User } from '../models/user.model';
import { persist } from 'zustand/middleware';
import { login, register } from '../service/auth.service';
import { IRegister } from '../models/auth.model';

interface AuthState {
  user: null | User;
  token: null | string;
  login: (email: string, password: string) => Promise<void>;
  register: (userInfo: IRegister) => Promise<void>;
  logout: () => void;
}

const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      user: null,
      token: null,
      login: async (email, password) => {
        const result = await login({ email: email, password: password });
        console.log('result', result)
        set({ token: result.access_token, user: result.user })
      },
      register: async (data) => {
        const result = await register(data);
        set({ token: result.access_token, user: result.user })
      },
      logout: () => {
        // Logout user code
      },
    }),
    {
      name: "auth",
    }
  )
);

export default useAuthStore;