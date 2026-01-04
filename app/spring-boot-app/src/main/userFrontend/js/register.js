        var ul = document.getElementById("addedUser");
        var userName = document.getElementById("multiple_name");
        var address = document.getElementById("multiple_address");
        let mySet = new Set();

        function addUser(){

            if (userName.value != "" && address.value != ""){

                var listItem = document.createElement("li");
                listItem.textContent = userName.value + ", "+ address.value;
                ul.append(listItem)

                                
                mySet.add({name : userName.value, address : address.value})
                console.log(mySet);
            }
        }

        let form = document.querySelector('multiUser');
        let multiUser = document.getElementById('multiUser_submit')
        multiUser.addEventListener('click', handleSubmit);

        let multiresultContainer = document.getElementById('multiUser_right_response');

        
        function handleSubmit(event){
            event.preventDefault();
            let myListArray = [...mySet];


            let apiURL = 'http://localhost:8080/users';

                fetch(apiURL, {
                    method: 'POST',
                    headers : {
                        'Content-Type' : 'application/json'
                    },
                    body : JSON.stringify(myListArray),
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
            multiresultContainer.innerHTML = '';

            var p = document.createElement('p');
            p.textContent = backendData;
            p.style.color = '#138d00ff';
            p.style.backgroundColor = '#ffffff';
            p.style.padding = '20px';
            p.style.borderRadius = '10px';
            multiresultContainer.append(p);
        })
        .catch(error => {
            // This block handles network errors or errors thrown in the .then() blocks
            console.error('Error during fetch operation: ', error);

            multiresultContainer.innerHTML = '';
            var p = document.createElement('p');
            p.textContent = error.message;
            p.style.color = '#f20000ff';
            p.style.backgroundColor = '#ffffff';
            p.style.padding = '20px';
            p.style.borderRadius = '10px';
            multiresultContainer.append(p);
        });

                mySet.clear();
        }