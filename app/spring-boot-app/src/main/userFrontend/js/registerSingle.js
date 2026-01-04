let singleform = document.getElementById('singleForm');
let SingleUser = document.getElementById('singleUser_submit');

SingleUser.addEventListener('click', handleSingleSubmit);

let resultContainer = document.getElementById('singleUser_right_response');

function handleSingleSubmit(event) {
    event.preventDefault();

    let formData = new FormData(singleform);
    let data = Object.fromEntries(formData);
    let jsonData = JSON.stringify(data);
    let apiURL = 'http://localhost:8080/user';

    fetch(apiURL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(async response => {
            // First, check if the request was successful
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(`${errorText}`); //${response.status} : 
            }
            return response.text();
        })
        .then(backendData => {
            // This block receives the actual data from the backend
            console.log('Success: ', backendData);

            // Clear previous messages if any
            resultContainer.innerHTML = '';

            var p = document.createElement('p');
            p.textContent = backendData;
            p.style.color = '#138d00ff';
            p.style.backgroundColor = '#ffffff';
            p.style.padding = '20px';
            p.style.borderRadius = '10px';
            resultContainer.append(p);
        })
        .catch(error => {
            // This block handles network errors or errors thrown in the .then() blocks
            console.error('Error during fetch operation: ', error);

            resultContainer.innerHTML = '';
            var p = document.createElement('p');
            p.textContent = error.message;
            p.style.color = '#f20000ff';
            p.style.backgroundColor = '#ffffff';
            p.style.padding = '20px';
            p.style.borderRadius = '10px';
            resultContainer.append(p);
        });
}

