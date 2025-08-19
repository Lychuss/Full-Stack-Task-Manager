const taskField = document.getElementById("taskField");
const ulList = document.getElementById("taskList");

const tasks = localStorage.getItem("tasks");
let array = tasks ? JSON.parse(tasks) : [];

await fetchDisplay();
addList();

export async function addTask() {
    const task = {
        tasks: '',
    }

    if (taskField.value != '') {
         task.tasks = taskField.value;
         await fetchAdd(task);
         await fetchDisplay();
         addList();
    } else {
        alert("You must input value!");
    }
}

export function addList() {
    ulList.innerHTML = '';
    for(const task of array){
        const element = document.createElement("li");
        const button = document.createElement("button");

        button.className = "delete-btn";
        button.id = task.id;
        button.innerText = "Done";

        element.id = task.id;
        element.textContent = task.tasks;

        console.log(task.tasks);

        ulList.appendChild(element).appendChild(button);
    }
}

export async function doneTasks(event){
    if(event.target.classList.contains("delete-btn")){
        event.target.parentElement.remove();
        const remove = event.target.id;
        await fetchDelete(remove);
    }
}

async function fetchAdd(task) {
    const response = await fetch("http://localhost:8080/first/api/taskmanager/tasks", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    });
}

async function fetchDisplay(){
    const response = await fetch("http://localhost:8080/first/api/taskmanager/alltasks");
    const data = await response.json();
    array = data;
    console.log(data);
    return data;
}

async function fetchDelete(id){
    const response = await(fetch(`http://localhost:8080/first/api/taskmanager/delete=${id}`, {
        method: "DELETE"
    }));
   await fetchDisplay();
   await addList();
}