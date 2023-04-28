export interface IResponse<T> {
    ok: boolean
    data: T
    message?: string
}