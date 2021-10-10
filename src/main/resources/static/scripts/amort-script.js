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

    console.log(loanAmount)
    console.log(apy)
    console.log(numPeriods)


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
        
        const container = document.createElement('p');
        container.innerText = element;
        parent.appendChild(container)
    });

}


