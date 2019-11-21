// On page load show all timesheets
document.addEventListener('DOMContentLoaded', function(){
    let promise = axios.get('http://localhost:8080/paper-chasers/api/timesheets')

    promise.then(function(response){
        // GET request succeeded
        console.log("Success")
        console.log(response.data)

    }) // End promise.then callback
    promise.catch(function(response){
        // GET request failed
        console.log("Success")
        console.log(response.data)
        
    }) // End promise.catch callback
}) // End DOMContentLoaded