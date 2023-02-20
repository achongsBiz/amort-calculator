document.addEventListener(
    'DOMContentLoaded',
    () => {
        
        const calcBtn = document.getElementById('submit');
        calcBtn.addEventListener('click', getData);

    }
)

function getData() {

    const loanAmount = document.getElementById('loan-amount').value;
    const apy = parseFloat(document.getElementById('apy').value) / 100;
    const numPeriods = document.getElementById('periods').value;

    fetch(`/api/amort?apy=${apy}&loanAmount=${loanAmount}&period=${numPeriods}`)
    .then(
        response => response.json()
    )
    .then(
        (data)=> {
                toggleDataSection("block");
                notify("");
                buildTable(data);
        }
    ). catch (
        ()=>{
            toggleDataSection("none");
            notify("One or more of the inputs might be invalid. Read the instructions and try again.");
        }
    );
}

function notify(message) {
    const noticeBox = document.getElementById("notification");
    noticeBox.innerText = message;

}

function toggleDataSection(toggleValue) {

    const data = document.getElementById("data");
    data.style.display=toggleValue;

}


function buildTable(dataArr) {

    const parent = document.getElementById('data');
    parent.innerHTML = "";

    const header = document.createElement('tr');

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

    parent.append(header);

    dataArr.forEach(element => {
        
        const container = document.createElement('tr');

        const periodContainer = document.createElement('td');
        periodContainer.innerText = element.period;
        container.append(periodContainer);

        const begBalContainer = document.createElement('td');
        begBalContainer.innerText = element.begBalance;
        container.append(begBalContainer);

        const principContainer = document.createElement('td');
        principContainer.innerText = element.principal;
        container.append(principContainer);

        const intContainer = document.createElement('td');
        intContainer.innerText = element.interest;
        container.append(intContainer);

        const endBalContainer= document.createElement('td');
        endBalContainer.innerText = element.endBalance;
        container.append(endBalContainer);

        parent.appendChild(container)
    });
}



