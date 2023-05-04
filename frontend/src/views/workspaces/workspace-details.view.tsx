import { useParams } from "react-router-dom";

export default function WorspaceDetails() {
    const { name } = useParams();

    return (
        <div className="flex flex-col flex-1 p-6">
            <h4 className="text-xl font-semibold">{name}</h4>

            <div className="mt-6">
                <div className="flex flex-col w-full lg:flex-row">
                    <div className="grid flex-grow h-32 card bg-base-300 rounded-box place-items-center">
                        <div className="form-control w-full max-w-xs">
                            <label className="label">
                                <span className="label-text">What is your name?</span>
                                <span className="label-text-alt">Top Right label</span>
                            </label>
                            <input type="text" placeholder="Type here" className="input input-bordered w-full max-w-xs" />
                            <label className="label">
                                <span className="label-text-alt">Bottom Left label</span>
                                <span className="label-text-alt">Bottom Right label</span>
                            </label>
                        </div>
                    </div>
                    <div className="divider lg:divider-horizontal"></div>
                    <div className="grid flex-grow h-32 card bg-base-300 rounded-box place-items-center">content</div>
                </div>
            </div>
        </div>
    )
}