import {useState} from "react";
import {addNewTask, deleteTask} from "@/api/taskService";
import {log} from "next/dist/server/typescript/utils";
import {Dialog, DialogActions, DialogContent, DialogContentText} from "@mui/material";
import Button from "@mui/material/Button";

const AddingTask = ({reloadTask, isUpdating, setIsUpdating}) => {

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [isTitle, setIsTitle] = useState(false);
    const [open, isOpen] = useState(false);
    const [isDeleting, setIsDeleting] = useState(false);

    function handleTitleChange(event) {
        setTitle(event.target.value);
        console.log(title)
    }

    function handleDescriptionChange(event) {
        setDescription(event.target.value);
        console.log(description)
    }


    const newTaskRequest = async (title, description) => {
        if (title === "") {
            isOpen(true);
            return;
        }
        let tmpDescription = ""
        description === "" ? tmpDescription = "Set description" : tmpDescription = description;
        const addNewTaskRequest = {
            "title": title,
            "description": tmpDescription
        }
        if(!isDeleting) {
            setIsDeleting(true);
            await addNewTask(addNewTaskRequest);
            setIsDeleting(false)
            reloadTask();
        }
    }


    function handleClose() {
        isOpen(false);
    }

    return (
        <div>
            <form>
                <Dialog
                    open={open}
                    keepMounted
                    onClose={handleClose}
                    aria-describedby="alert-dialog-slide-description"
                >
                    <DialogContent>
                        <DialogContentText id="alert-dialog-slide-description">
                            You must set task title
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose}>Ok</Button>
                    </DialogActions>
                </Dialog>
                <div className={"custom_input"}>
                    <input className={"input"} type="text" value={title} onChange={handleTitleChange}/>
                </div>
                <br/>
                <div className={"custom_input"}>
                    <input className={"input"} type="text" value={description} onChange={handleDescriptionChange}/>

                    <div className={"button-div-test"}>

                        <button disabled={isTitle} className={"btn"} type={"button"}
                                onClick={event => newTaskRequest(title, description)}>Add task
                        </button>
                    </div>

                </div>
            </form>
        </div>
    )

}
export default AddingTask