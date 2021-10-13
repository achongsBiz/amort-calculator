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
        data => buildTable(data)
    );
}

function buildTable(dataArr) {

    const parent = document.getElementById('data');

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



