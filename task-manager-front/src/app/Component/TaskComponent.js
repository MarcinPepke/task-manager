import React, { useState } from 'react';
import Button from "@mui/material/Button";
import {Clear, DoneOutlined} from "@mui/icons-material";


const TaskComponent = ({ item, handleUpdateTask, setUnfinishTask, setFinishTask, deleteTask, }) => {
    const [title, setTitle] = useState(item.tittle);
    const [description, setDescription] = useState(item.description);




    return (
        <div className="task-div" key={item.id}>
            <h1 className="title-div">
        <span
            contentEditable={true}
            onBlur={() => handleUpdateTask(title, item.description, item.id)}
            onInput={(e) => setTitle(e.target.innerText)}
        >
          {item.tittle}
        </span>
                <div className="button-div">
                    <Button
                        className="btn-finish"
                        style={{ color: 'black' }}
                        onClick={() => (item.isFinished ? setUnfinishTask(item.id) : setFinishTask(item.id))}
                        endIcon={<DoneOutlined />}
                    >
                        {item.isFinished ? 'FINISHED' : 'UNFINISHED'}
                    </Button>
                    <Button
                        onClick={() => deleteTask(item.id)}
                        endIcon={<Clear />}
                        type="button"
                        style={{ color: 'black' }}
                        className="task-button-delete"
                    ></Button>
                </div>
            </h1>
            <h2>
        <span
            contentEditable={true}
            onBlur={() => handleUpdateTask(item.tittle, description, item.id)}
            onInput={(e) => setDescription(e.target.innerText)}
        >
          {item.description}
        </span>
            </h2>
        </div>
    );
};

export default TaskComponent;
