let currentPage = 0;
let pages = [];

document.addEventListener(
    'DOMContentLoaded',
    () => {     
        const calcBtn = document.getElementById("submit");
        calcBtn.addEventListener("click", getData);
                                              
        const firstBtn = document.getElementById("goToFirstBtn");
        firstBtn.addEventListener("click", goToFirstPage);

        const nextBtn = document.getElementById("goNextBtn");
        nextBtn.addEventListener("click", goToNext);

        const prevBtn = document.getElementById("goPreviousBtn");
        prevBtn.addEventListener("click", goToPrevious);

        const lastBtn = document.getElementById("goLastBtn");
        lastBtn.addEventListener("click", goToLastPage);

        const gotoBtn = document.getElementById("goToPageBtn");
        gotoBtn.addEventListener("click", goToPage);

    }

)

function getData() {

    const loanAmount = document.getElementById("loan-amount").value;
    const apy = parseFloat(document.getElementById("apy").value) / 100;
    const numPeriods = document.getElementById("periods").value;

    pages=[];

    fetch(`/api/amort?apy=${apy}&loanAmount=${loanAmount}&period=${numPeriods}`)
    .then(
        (response) => {

            if (!response.ok) {
                return Promise.reject("invalid data");
            }

            return response.json();
        }
    )
    .then(
        (data)=> {

            toggleSection("data", true);
            toggleSection("pagination", true);

            notify("");
            //turnOnControls();
            let temp = [];
                    
            if(data.length < 11) {  
                pages.push( buildTable(data) );

            } else {        
                let itemsProcessed = 0;
    
                for(let i=0; (i < data.length) || !(data.length - itemsProcessed < 10); i++) {
                    temp.push(data[i]);
                    itemsProcessed++;
    
                    // 10 items per page
                    if(temp.length === 10) {
                        pages.push( buildTable(temp) );
                        temp = [];
                    }
                }
    
                // push remaining items
                for(let i= data[data.length-10]; i < data.length; i++) {
                    temp = data[i];
                }
    
                if(temp.length != 0) {
                    pages.push( buildTable(temp) );
                }
            }

            updatePageCount();
            goToFirstPage();

        }
    ).catch (
        ()=>{

            toggleSection("data", false);
            toggleSection("pagination", false);
            notify("One or more of the inputs might be invalid. Read the instructions and try again.");
            
        }

    );
}

function goToFirstPage() {
    renderPage(pages[0]);
    currentPage = 0;

}


function goToLastPage() {
    renderPage(pages[pages.length-1]);
    currentPage = pages.length-1;

}

function goToNext() {
    if(currentPage === pages.length - 1) {
        return;
    } 

    renderPage(pages[++currentPage]);

}

function goToPrevious() {
    if(currentPage === 0) {
        return;
    } 

    renderPage(pages[--currentPage]);

}


function goToPage() {
    const pageCountInput = document.getElementById("pageCount");
    const pageTotal = parseInt(pageCountInput.value);

    const targetPageInput = document.getElementById("targetPage");
    const targetPage = parseInt(targetPageInput.value);

    if(!regexMatch("^[0-9]*$", targetPage) || targetPageInput.value === undefined || targetPage > pageTotal || targetPage < 1) {
        window.alert("Invalid page value.");
        return;
    }

    currentPage = targetPage - 1;

    renderPage(pages[currentPage]);

}

function updatePageCount() {
    const pageCountInput = document.getElementById("pageCount");
    pageCountInput.value = pages.length;
}


function renderPage(page) {
    const parent = document.getElementById("data");
    parent.innerHTML = "";

    parent.appendChild(page);

}


function notify(message) {
    const noticeBox = document.getElementById("notification");
    noticeBox.innerText = message;

}


function toggleSection(elementName, activate) {

    const section = document.getElementById(elementName);

    section.style.display = activate === true ? "block" : "none";

}

function toggleDataSection(toggleValue) {
    const data = document.getElementById("data");
    data.style.display=toggleValue;

}

function togglePagination(toggleValue) {
    const data = document.getElementById("pagination");
    data.style.display=toggleValue;
}


function buildTable(dataArr) {
    const pageTable = document.createElement("table");

    const header = document.createElement("tr");

    const periodHeader = document.createElement("th");
    periodHeader.innerText = "Period";
    header.appendChild(periodHeader);

    const begBalanceHeader = document.createElement("th");
    begBalanceHeader.innerText = "Beginning Balance";
    header.appendChild(begBalanceHeader);

    const principalHeader = document.createElement("th");
    principalHeader.innerText = "Principal";
    header.appendChild(principalHeader);

    const interestHeader = document.createElement("th");
    interestHeader.innerText = "Interest";
    header.appendChild(interestHeader);

    const endBalanceHeader = document.createElement("th");
    endBalanceHeader.innerText = "Ending Balance";
    header.appendChild(endBalanceHeader);

    pageTable.append(header);

    for(let i=0; i < dataArr.length; i++) {

        const element = dataArr[i];

        const container = document.createElement("tr");

        const periodContainer = document.createElement("td");
        periodContainer.innerText = element.period;
        container.append(periodContainer);

        const begBalContainer = document.createElement("td");
        begBalContainer.innerText = element.begBalance;
        container.append(begBalContainer);

        const principContainer = document.createElement("td");
        principContainer.innerText = element.principal;
        container.append(principContainer);

        const intContainer = document.createElement("td");
        intContainer.innerText = element.interest;
        container.append(intContainer);

        const endBalContainer= document.createElement("td");
        endBalContainer.innerText = element.endBalance;
        container.append(endBalContainer);

        pageTable.appendChild(container)
    }

    return pageTable;
    
}

function regexMatch(pattern, target) {
    let re = new RegExp(pattern);
    return re.test(target);
 }



