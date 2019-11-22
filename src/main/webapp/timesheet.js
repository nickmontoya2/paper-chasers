// On page load show all timesheets
document.addEventListener('DOMContentLoaded', function(){
    let promise = axios.get('http://localhost:8080/paper-chasers/api/timesheets')

    promise.then(function(response){
        // GET request succeeded
        console.log("Success")
        console.log(response.data)
        addMultipleTimesheets(response.data)
    }) // End promise.then callback
    promise.catch(function(response){
        // GET request failed
        console.log("Failed")
        console.log(response.data)
        
    }) // End promise.catch callback

}) // End DOMContentLoaded

// Make form when user clicks button to add new timesheet
document.getElementById('newTimesheetButton').addEventListener('click', function(e){
    console.log("New Timesheet form requested")

    let newForm = document.createElement("form")
    newForm.setAttribute('method', "post")
    newForm.setAttribute('action', '/paper-chasers/api/saveTimesheet')
    // inputs
    let mHours = document.createElement('input')
    mHours.setAttribute('type', "text")
    mHours.setAttribute('name', "monday_hours")
    mHours.setAttribute('value', "Monday Hours")
    let tHours = document.createElement("input")
    tHours.setAttribute('type', "text")
    tHours.setAttribute('name', "tuesday_hours")
    tHours.setAttribute('value', "Tuesday Hours")
    let wHours = document.createElement("input")
    wHours.setAttribute('type', "text")
    wHours.setAttribute('name', "wednesday_hours")
    wHours.setAttribute('value', "Wednesday Hours")
    let rHours = document.createElement("input")
    rHours.setAttribute('type', "text")
    rHours.setAttribute('name', "thursday_hours")
    rHours.setAttribute('value', "Thursday Hours")
    let fHours = document.createElement("input")
    fHours.setAttribute('type', "text")
    fHours.setAttribute('name', "friday_hours")
    fHours.setAttribute('value', "Friday Hours")
    let weekEnding = document.createElement("input")
    weekEnding.setAttribute('type', "text")
    weekEnding.setAttribute('name', "week_ending")
    weekEnding.setAttribute('value', "Week Ending Date")
    let submit = document.createElement('input')
    submit.setAttribute('type', "submit")
    submit.setAttribute('value', "Save Timesheet")
    // Put all inputs in the form
    newForm.appendChild(mHours)
    newForm.appendChild(tHours)
    newForm.appendChild(wHours)
    newForm.appendChild(rHours)
    newForm.appendChild(fHours)
    newForm.appendChild(weekEnding)
    newForm.appendChild(submit)
    // Add whole form to div
    document.getElementById('newForms').appendChild(newForm)
}) // End newTimesheetButton click event


/* 
*
* Functions to add timesheets onto existing row
*
*/

function addMultipleTimesheets(list){
    for(let timesheet of list) {
        console.log(timesheet)
        appendTimesheet(timesheet)
    }
}

function appendTimesheet(timesheet) {
    // New row
    let tr = document.createElement('tr')
    // new elements within row
    let id = document.createElement('td')
    id.innerText = timesheet.timesheet_ID
    let monday = document.createElement('td')
    monday.innerText = timesheet.monday_hours
    let tuesday = document.createElement('td')
    tuesday.innerText = timesheet.tuesday_hours
    let wednesday = document.createElement('td')
    wednesday.innerText = timesheet.wednesday_hours
    let thursday = document.createElement('td')
    thursday.innerText = timesheet.thursday_hours
    let friday = document.createElement('td')
    friday.innerText = timesheet.friday_hours
    let week_ending = document.createElement('td')
    week_ending.innerText = timesheet.week_ending
    // check value of status and switch to either 'saved' or 'submitted'
    let status = document.createElement('td')
    if (timesheet.status == 1) {
        status.innerText = "Saved"
        // Add buttons for edit, delete, submit here
        // edit button
        let editButton = document.createElement('button')
        editButton.setAttribute('type', "button")
        editButton.setAttribute('value', timesheet.timesheet_ID)
        editButton.setAttribute('onclick', "editTimesheet(this)")
        editButton.innerText = "Edit"
        status.appendChild(editButton)
        // delete button
        let deleteButton = document.createElement('button')
        deleteButton.setAttribute('type', "button")
        deleteButton.setAttribute('value', timesheet.timesheet_ID)
        deleteButton.setAttribute('onclick', "deleteTimesheet(this)")
        deleteButton.innerText = "Delete"
        status.appendChild(deleteButton)
        // submit button
        let submitButton = document.createElement('button')
        submitButton.setAttribute('type', "button")
        submitButton.setAttribute('value', timesheet.timesheet_ID)
        submitButton.setAttribute('onclick', "submitTimesheet(this)")
        submitButton.innerText = "Submit Timesheet"
        status.appendChild(submitButton)

    } else if (timesheet.status == 2) {
        // No need for edit, delete, submit buttons for this 
        status.innerText = "Submitted"
    }

    // Append everything into the new row
    tr.appendChild(id);
    tr.appendChild(monday);
    tr.appendChild(tuesday);
    tr.appendChild(wednesday);
    tr.appendChild(thursday);
    tr.appendChild(friday);
    tr.appendChild(week_ending);
    tr.appendChild(status);
    // Append the new row into the table
    document.getElementById('timesheets').appendChild(tr)
}

function editTimesheet(editButton){
    console.log("Pressed edit button for timesheet_ID: ", editButton.value)
    // set up functionality to edit timesheet
}

function deleteTimesheet(deleteButton){
    console.log("Pressed delete button for timesheet_ID: ", deleteButton.value)
    // set up functionality to delete timesheet
}

function submitTimesheet(submitButton) {
    console.log("Pressed submit button for timesheet_ID: ", submitButton.value)
    // set up functionality to submit timesheet
    // PUT request to api/timesheets
    let tsID = {
        timesheet_ID: submitButton.value
    }
    let promise = axios.put('http://localhost:8080/paper-chasers/api/timesheets', tsID)
    promise.then(function(response){
        // put request succeeded
        console.log("PUT request succeeded")
        // remove buttons, Submitted
    })
    promise.catch(function(response){
        console.log("PUT request failed: ", response) 
        //you are logging the response but not handling it, catch is only for errors, you might can do a || "" to say do nothing if no error
        // indicate a failure, maybe why
    })
}