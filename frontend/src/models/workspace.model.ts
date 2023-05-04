export interface IWorkspace {
    name: string
    description: string
    color?: string
    url: string
    template: string
}

export interface ICreateWorkspace {
    name: string
    description: string
    template: string
    port: number
}