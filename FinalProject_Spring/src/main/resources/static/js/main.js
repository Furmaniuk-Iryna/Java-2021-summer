document.getElementById('addRequest').addEventListener('click', async () => {
    const type_en = document.getElementById('type_en').value;
    const weight = document.getElementById('weight').value;
    const length = document.getElementById('length').value;
    const width = document.getElementById('width').value;
    const height = document.getElementById('height').value;
    const address = document.getElementById('address').value;

    if (weight != null) {
        const res = await fetch('http://localhost:8090/deliveryRequests/edit', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "type_en": type_en,
                "weight": weight,
                "length": length,
                "width": width,
                "height": height,
                "address": {"id": address}
            })
        });
        const resp = await res.json();
        requestsToHTML(resp);
        document.getElementById('weight').value = '';
        document.getElementById('length').value = '';
        document.getElementById('width').value = '';
        document.getElementById('height').value = '';
    }
})


async function getAllRequests() {
    const res = await fetch('http://localhost:8090/deliveryRequests/edit');
    const requests = await res.json();
    console.log(requests);
    requests.forEach(request => requestsToHTML(request))
}

window.addEventListener('DOMContentLoaded', getAllRequests);

function requestsToHTML(request) {
    const requestsList = document.getElementById('request-elements');

    requestsList.insertAdjacentHTML('beforeend', `
     <tr id="request${request.id}">
       <td>${request.id}</td>
        <td>${request.user.username}</td>
        <td>${request.type_en}</td> 
        <td>${request.weight}</td>
        <td>${request.volume}</td>
        <td>${request.address.address_en}</td>
        <td>${request.dateOfArrival}</td>
        <td><button onclick="deleteRequest(${request.id})" type="button" class="btn-info" id="deleteRequest">Delete</button></td>
        <td><button onclick="toUpdatePage(${request.id})" type="button" class="btn-info">Update</button></td>
              </tr>
    `);
}

function toUpdatePage(id) {
    window.location.replace(`http://localhost:8090/deliveryRequests/update/${id}`)
}

async function deleteRequest(id) {
    const res = await fetch(`http://localhost:8090/deliveryRequests/edit/${id}`, {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json'}
        }
    );
    const data = await res.json();
    console.log(data);
    if (data) {
        document.getElementById(`request${id}`).remove();
    }
    location.reload();
}


async function updateRequest(id) {
    const id_request = document.getElementById('id-request').value;
    const type_en = document.getElementById('type_en').value;
    const weight = document.getElementById('weight').value;
    const length = document.getElementById('length').value;
    const width = document.getElementById('width').value;
    const height = document.getElementById('height').value;
    const address = document.getElementById('address').value;

    if (weight != null) {
        const res = await fetch(`http://localhost:8090/deliveryRequests/edit/${id_request}`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "type_en": type_en,
                "weight": weight,
                "length": length,
                "width": width,
                "height": height,
                "address": {"id": address}
            })
        });
        window.location.replace(`http://localhost:8090/deliveryRequests/editor`)
        const re = await res.json();
        requestsToHTML(re);

    }
}

async function findRequestById() {

    const id = document.getElementById('id').value;
    const res = await fetch(`http://localhost:8090/deliveryRequests/edit/${id}`);
    const neededRequest = await res.json();

    const requestsList = document.getElementById('requestById');

    requestsList.insertAdjacentHTML('beforeend', `
     <tr id="request${neededRequest.id}">
       <td>${neededRequest.id}</td>
        <td>${neededRequest.user.username}</td>
        <td>${neededRequest.type_en}</td> 
        <td>${neededRequest.weight}</td>
        <td>${neededRequest.volume}</td>
        <td>${neededRequest.address.address_en}</td>
        <td>${neededRequest.dateOfArrival}</td>
        <td><button onclick="deleteRequest(${neededRequest.id})" type="button" class="btn-info" id="deleteRequest">Delete</button></td>
        <td><button onclick="toUpdatePage(${neededRequest.id})" type="button" class="btn-info">Update</button></td>
        <td><button onclick="document.getElementById(\`request${neededRequest.id}\`).remove()" type="button" class="btn-info">Close</button></td>
              </tr>
    `);
}