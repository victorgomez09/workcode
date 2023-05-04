import { SubmitHandler, useForm } from "react-hook-form";
import { ILogin } from "../models/auth.model";
import useAuthStore from "../store/user.store";
import { useNavigate } from "react-router-dom";

export default function Login() {
    const navigate = useNavigate()
    const { login } = useAuthStore(state => ({ login: state.login }))
    const { register, handleSubmit, formState: { errors } } = useForm<ILogin>({
        mode: 'onTouched'
    });
    const onSubmit: SubmitHandler<ILogin> = async data => {
        try {
            await login(data.email, data.password)
            navigate("/app")
        } catch (e) {
            console.log(e)
        }
    };

    return (
        <div className="flex flex-1 items-center justify-center">
            <form className="w-1/3 overflow-hidden rounded border shadow-md bg-white text-slate-500 shadow-slate-200" onSubmit={handleSubmit(onSubmit)}>
                <div className="p-6">
                    <header className="mb-4 text-center">
                        <h3 className="text-xl font-medium text-slate-700">Login</h3>
                    </header>
                    <div className="flex flex-col space-y-8">
                        <div className="relative my-6">
                            <input
                                id="email"
                                type="email"
                                placeholder="your name"
                                className={`peer relative h-10 w-full rounded border px-4 text-sm text-slate-500 placeholder-transparent outline-none transition-all autofill:bg-white focus:border-indigo-500 focus:outline-none disabled:cursor-not-allowed disabled:bg-slate-50 disabled:text-slate-400 
                                ${errors.email ? "border-pink-500 invalid:text-pink-500 focus:border-pink-500" : "border-slate-200"}`}
                                {...register("email", {
                                    required: {
                                        value: true,
                                        message: "Email is required"
                                    },
                                    pattern: {
                                        value: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                                        message: "Enter valid email"
                                    }
                                })}
                            />
                            <label
                                htmlFor="email"
                                className={`absolute left-2 -top-2 z-[1] px-2 text-xs transition-all before:absolute before:top-0 before:left-0 before:z-[-1] before:block before:h-full before:w-full before:bg-white before:transition-all peer-placeholder-shown:top-2.5 peer-placeholder-shown:text-sm peer-focus:-top-2 peer-focus:text-xs peer-focus:text-indigo-500 peer-disabled:cursor-not-allowed peer-disabled:text-slate-400 peer-disabled:before:bg-transparent 
                                ${errors.email ? "after:text-pink-500 peer-focus:text-pink-500 text-pink-500" : "text-slate-400"}`}
                            >
                                Your email
                            </label>
                            {errors.email?.message && (
                                <small className={`absolute flex w-full justify-between px-4 py-1 text-xs transition ${errors.email ? "text-pink-500" : "text-slate-400"}`}>
                                    <span>{errors.email.message}</span>
                                </small>
                            )}
                        </div>

                        <div className="relative my-6">
                            <input
                                id="password"
                                type="password"
                                placeholder="your password"
                                className={`peer relative h-10 w-full rounded border px-4 pr-12 text-sm text-slate-500 placeholder-transparent outline-none transition-all autofill:bg-white focus:border-indigo-500 focus:outline-none disabled:cursor-not-allowed disabled:bg-slate-50 disabled:text-slate-400
                                ${errors.password ? "border-pink-500 text-pink-500 focus:border-pink-500 " : "border-slate-200"}`}
                                {...register("password", {
                                    required: {
                                        value: true,
                                        message: "Password is required"
                                    }
                                })}
                            />
                            <label
                                htmlFor="password"
                                className={`absolute left-2 -top-2 z-[1] px-2 text-xs transition-all before:absolute before:top-0 before:left-0 before:z-[-1] before:block before:h-full before:w-full before:bg-white before:transition-all peer-placeholder-shown:top-2.5 peer-placeholder-shown:text-sm peer-focus:-top-2 peer-focus:text-xs peer-focus:text-indigo-500 peer-disabled:cursor-not-allowed peer-disabled:text-slate-400 peer-disabled:before:bg-transparent 
                                ${errors.password ? "after:text-pink-500  text-pink-500 peer-focus:text-pink-500" : "text-slate-400"}`}
                            >
                                Your password
                            </label>
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                className="absolute top-2.5 right-4 h-5 w-5 cursor-pointer stroke-slate-400 peer-disabled:cursor-not-allowed"
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                                strokeWidth="1.5"
                            >
                                <path
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.88 9.88l-3.29-3.29m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21"
                                />
                            </svg>
                            {errors.password && (
                                <small className={`absolute flex w-full justify-between px-4 py-1 text-xstransition ${errors.password ? "text-pink-500" : "text-slate-400"}`}>
                                    <span>{errors.password.message}</span>
                                </small>
                            )}
                        </div>
                    </div>
                </div>

                <div className="flex justify-end p-6 ">
                    <button className="inline-flex h-10 w-full items-center justify-center gap-2 whitespace-nowrap rounded bg-indigo-500 px-5 text-sm font-medium tracking-wide text-white transition duration-300 hover:bg-indigo-600 focus:bg-indigo-700 focus-visible:outline-none disabled:cursor-not-allowed disabled:border-indigo-300 disabled:bg-indigo-300 disabled:shadow-none">
                        <span>Send</span>
                    </button>
                </div>
            </form>
        </div>
    )
}