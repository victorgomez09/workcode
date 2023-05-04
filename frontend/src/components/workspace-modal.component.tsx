import { SubmitHandler, useForm } from "react-hook-form";

import { ICreateWorkspace, IWorkspace } from "../models/workspace.model";
import { createWorkspace } from "../service/workspace.service";
import useAuthStore from "../store/user.store";
import { useCustomMutation } from "../hooks/query.hook";
import { useEffect, useState } from "react";

export const WorkspaceModal = () => {
    const [created, setCreated] = useState(false)
    const token = useAuthStore(state => state.token)
    const { register, handleSubmit, formState: { errors }, reset } = useForm<ICreateWorkspace>({
        mode: 'onTouched'
    });
    const { mutate, isSuccess } = useCustomMutation<{ data: ICreateWorkspace, token: string }, IWorkspace>(() => createWorkspace({
        name: "",
        description: "",
        template: "",
        port: 0
    }, ""), 'get-workspaces')
    const useSubmit: SubmitHandler<ICreateWorkspace> = async data => {
        console.log(data)
        mutate({ data, token: token || "" })
    };

    useEffect(() => {
        if (isSuccess) setCreated(true)

        reset()
    }, [isSuccess, reset])

    return (
        <>
            <label htmlFor="workspace-modal" className="btn btn-sm" onClick={() => setCreated(false)}>Create workspace</label>

            {!created && (
                <>
                    <input type="checkbox" id="workspace-modal" className="modal-toggle" />
                    <label htmlFor="workspace-modal" className="modal modal-bottom sm:modal-middle cursor-pointer">
                        <label className="modal-box relative" htmlFor="">
                            <h3 className="text-lg font-bold">Create new workspace</h3>
                            <form className="py-4" noValidate onSubmit={handleSubmit(useSubmit)}>
                                <div className="form-control w-full">
                                    <label className="label">
                                        <span className={`label-text ${errors.name ? "text-error" : ""}`}>Name</span>
                                    </label>
                                    <input type="text" placeholder="Type here" className={`input input-bordered w-full ${errors.name ? "input-error" : ""}`} {...register("name", {
                                        required: {
                                            value: true,
                                            message: "Name must be provided"
                                        }
                                    })} />
                                    {errors.name && (
                                        <label className="label">
                                            <span className="label-text-alt text-error italic">{errors.name.message}</span>
                                        </label>
                                    )}
                                </div>

                                <div className="form-control w-full">
                                    <label className="label">
                                        <span className={`label-text ${errors.template ? "text-error" : ""}`}>Template</span>
                                    </label>
                                    <select defaultValue="" className={`select select-bordered w-full ${errors.template ? "select-error" : ""}`} {...register("template", {
                                        required: {
                                            value: true,
                                            message: "Template must be required"
                                        }
                                    })}>
                                        <option disabled value="">Pick one</option>
                                        <option value="code-server">Code server</option>
                                    </select>
                                    {errors.template && (
                                        <label className="label">
                                            <span className="label-text-alt text-error italic">{errors.template.message}</span>
                                        </label>
                                    )}
                                </div>

                                <div className="form-control w-full">
                                    <label className="label">
                                        <span className={`label-text ${errors.description ? "text-error" : ""}`}>Description</span>
                                    </label>
                                    <textarea rows={2} placeholder="Type here" className={`textarea textarea-bordered w-full ${errors.name ? "textarea-error" : ""}`} {...register("description", {
                                        required: {
                                            value: true,
                                            message: "Description must be provided"
                                        }
                                    })} />
                                    {errors.description && (
                                        <label className="label">
                                            <span className="label-text-alt text-error italic">{errors.description.message}</span>
                                        </label>
                                    )}
                                </div>

                                <div className="form-control w-full">
                                    <label className="label">
                                        <span className={`label-text ${errors.port ? "text-error" : ""}`}>Port</span>
                                    </label>
                                    <input type="number" min={1000} placeholder="Type here" className={`input input-bordered w-full ${errors.port ? "input-error" : ""}`} {...register("port", {
                                        required: {
                                            value: true,
                                            message: "Port must be provided"
                                        },
                                        valueAsNumber: true
                                    })} />
                                    {errors.port && (
                                        <label className="label">
                                            <span className="label-text-alt text-error italic">{errors.port.message}</span>
                                        </label>
                                    )}
                                </div>

                                <div className="modal-action">
                                    <button type="submit" className="btn">Send</button>
                                </div>
                            </form>
                        </label>
                    </label>
                </>
            )}
        </>
    )
}