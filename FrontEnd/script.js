import * as service from '../FrontEnd/service.js';

service.addList();

const addTaskBtn = document.getElementById("addTaskBtn");
const ulList = document.getElementById("taskList");

addTaskBtn.addEventListener("click", () => {
   service.addTask();
});

ulList.addEventListener("click", function(event){
    service.doneTasks(event);
});
