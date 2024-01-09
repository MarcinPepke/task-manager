"use client"
import {Fragment} from "react";
import DeleteIcon from '@mui/icons-material/Delete';
import {deleteTask, getAllTasks, setFinishTask, setUnfinishTask, updateTask} from "@/api/taskService";
import Button from '@mui/material/Button';
import EasyEdit from 'react-easy-edit';

import {useState, useEffect} from "react";
import {Clear, Done, DoneOutlined, MoreHoriz} from "@mui/icons-material";
import AddingTask from "@/app/Component/AddingTask";
import TaskComponent from "@/app/Component/TaskComponent";


const Page = () => {

    const [tasks, setTasks] = useState([]);
    const [description, setDescription] = useState("");
    const [title, setTitle] = useState("");
    const [isSorted, setIsSorted] = useState(false);
    const [sortedTasks, setSortedTasks] = useState([]);
    const [isUpdating, setIsUpdating] = useState(false);


    useEffect(() => {
        fetchTaskList()
    }, [])

    const fetchTaskList = async () => {
        fetch("/task/all")
            .then(response => {
                return response.json()
            })
            .then(data => {
                setTasks([...data].sort((a, b) => a.id - b.id))
            })

    }



    function handleUpdateTask(title, description, taskId) {
        let tmpTitle = "";
        let tmpDescription = "";
        title === "" ? tmpTitle = "Set title" : tmpTitle = title;
        description === "" ? tmpDescription = "Set description" : tmpDescription = description;

        const updateTaskRequest = {
            "taskId": taskId,
            "title": tmpTitle,
            "description": tmpDescription
        }
        updateTask(updateTaskRequest).then((updatedTask) => {

            const updatedTasks = tasks.map((task) => (task.id === updatedTask.id ? updatedTask : task));
            setTasks(updatedTasks);

        })
            .catch((error) => console.error('Update error:', error));

        setTitle("");
        setDescription("")
    }

    const handleShowFinishedTasks = () => {
        if (!isSorted) {
            const sortedTasks = [...tasks].sort((a, b) => (a.isFinished === b.isFinished ? 1 : b.isFinished ? 0 : -1));
            setSortedTasks(sortedTasks);
            setIsSorted(true);
        } else {
            setSortedTasks([]);
            setIsSorted(false);
        }
    };

    const handleFinishTask = async (taskId) => {
        if (!isUpdating) {
            setIsUpdating(true);
            await setFinishTask(taskId);
            setIsUpdating(false);
            fetchTaskList();
        }
    };

    const handleUnfinishTask = async (taskId) => {
        if (!isUpdating) {
            setIsUpdating(true);
            await setUnfinishTask(taskId);
            setIsUpdating(false);
            fetchTaskList();
        }
    };
    const handleDeleteTask = async (taskId) => {
        if (!isUpdating) {
            setIsUpdating(true);
            await deleteTask(taskId);
            setIsUpdating(false);
            fetchTaskList();
        }
    }



    return (

        <div className={"body"}>
            <div>
                <center>
                    <header><h1>TASK MANAGER</h1></header>
                </center>
                <Button className={"btn"} onClick={handleShowFinishedTasks}>
                    Show finished tasks
                </Button>
                <br/>
            </div>
            <div>
                <AddingTask
                    reloadTask={fetchTaskList}

                />
            </div>
            <div>
                <div className="container">
                    {(isSorted ? sortedTasks : tasks).map((item, i) => (
                        <TaskComponent
                            key={item.id}
                            item={item}
                            handleUpdateTask={handleUpdateTask}
                            setUnfinishTask={handleUnfinishTask}
                            setFinishTask={handleFinishTask}
                            deleteTask={handleDeleteTask}
                        />
                    ))}
                </div>
                ;
            </div>
        </div>

    )
}
export default Page