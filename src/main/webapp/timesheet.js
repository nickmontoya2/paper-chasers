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
    weekEnding.setAttribute('name', "weekEnding")
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
    let timesheetRowID = "timesheetRow" + timesheet.timesheet_ID
    tr.setAttribute('id', timesheetRowID)
    // new elements within row
    let id = document.createElement('td')
    id.innerText = timesheet.timesheet_ID
    let monday = document.createElement('td')
    monday.setAttribute('id', "mHours" + timesheet.timesheet_ID)
    monday.setAttribute('value', timesheet.monday_hours)
    monday.innerText = timesheet.monday_hours
    let tuesday = document.createElement('td')
    tuesday.setAttribute('id', "tHours" + timesheet.timesheet_ID)
    tuesday.setAttribute('value', timesheet.tuesday_hours)
    tuesday.innerText = timesheet.tuesday_hours
    let wednesday = document.createElement('td')
    wednesday.setAttribute('id', "wHours" + timesheet.timesheet_ID)
    wednesday.setAttribute('value', timesheet.wednesday_hours)
    wednesday.innerText = timesheet.wednesday_hours
    let thursday = document.createElement('td')
    thursday.setAttribute('id', "rHours" + timesheet.timesheet_ID)
    thursday.setAttribute('value', timesheet.thursday_hours)
    thursday.innerText = timesheet.thursday_hours
    let friday = document.createElement('td')
    friday.setAttribute('id', "fHours" + timesheet.timesheet_ID)
    friday.setAttribute('value', timesheet.friday_hours)
    friday.innerText = timesheet.friday_hours
    let weekEnding = document.createElement('td')
    weekEnding.setAttribute('id', "weekEndingID" + timesheet.timesheet_ID)
    weekEnding.setAttribute('value', timesheet.week_ending)
    weekEnding.innerText = timesheet.week_ending
    // check value of status and switch to either 'saved' or 'submitted'
    let status = document.createElement('td')
    let statusID = "statusElement" + timesheet.timesheet_ID
    status.setAttribute('id', statusID)
    
    if (timesheet.status == 1) {
        status.innerText = "Saved"
        // Add buttons for edit, delete, submit here
        // edit button
        let editButton = document.createElement('button')
        editButton.setAttribute('type', "button")
        editButton.setAttribute('value', timesheet.timesheet_ID)
        editButton.setAttribute('onclick', "editTimesheet(this)")
        editButton.innerText = "Edit"
        let editID = "editButton" + timesheet.timesheet_ID
        editButton.setAttribute('id', editID)
        status.appendChild(editButton)
        // delete button
        let deleteButton = document.createElement('button')
        deleteButton.setAttribute('type', "button")
        deleteButton.setAttribute('value', timesheet.timesheet_ID)
        deleteButton.setAttribute('onclick', "deleteTimesheet(this)")
        deleteButton.innerText = "Delete"
        let deleteID = "deleteButton" + timesheet.timesheet_ID
        deleteButton.setAttribute('id', deleteID)
        status.appendChild(deleteButton)
        // submit button
        let submitButton = document.createElement('button')
        submitButton.setAttribute('type', "button")
        submitButton.setAttribute('value', timesheet.timesheet_ID)
        submitButton.setAttribute('onclick', "submitTimesheet(this)")
        submitButton.innerText = "Submit Timesheet"
        let submitID = "submitButton" + timesheet.timesheet_ID
        submitButton.setAttribute('id', submitID)
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
    tr.appendChild(weekEnding);
    tr.appendChild(status);
    // Append the new row into the table
    document.getElementById('timesheets').appendChild(tr)
}

/*
* Functions to edit, delete, and submit a timesheet
*/

function editTimesheet(editButton){
    console.log("Pressed edit button for timesheet_ID: ", editButton.value)    

    // set up functionality to edit timesheet
    // 1. Get current values based on timesheet ID
    var mHours = document.getElementById('mHours' + editButton.value).getAttribute("value")
    console.log(mHours)
    // 2. Change the row to have 5 inputs & save button
    removeElement("mHours" + editButton.value)
    removeElement("tHours" + editButton.value)
    removeElement("wHours" + editButton.value)
    removeElement("rHours" + editButton.value)
    removeElement("fHours" + editButton.value)
    removeElement("editButton" + editButton.value)
    removeElement("deleteButton" + editButton.value)
    removeElement("submitButton" + editButton.value)
    removeElement("weekEndingID" + editButton.value)
    removeElement("statusElement" + editButton.value)
    let mHoursInput = document.createElement('input')
    mHoursInput.setAttribute('type', "text")
    mHoursInput.setAttribute('placeholder', mHours)

    document.getElementById('timesheetRow' + editButton.value).appendChild(mHoursInput)
    // 3. On save send put request to update displayed values
    // 4. On successful PUT reupdate the row to show correct values along with edit, delete, submit buttons
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
        // remove buttons, change text to Submitted
        removeElement("editButton"+submitButton.value)
        removeElement("deleteButton"+submitButton.value)
        removeElement("submitButton"+submitButton.value)
        document.getElementById('statusElement'+submitButton.value).innerText = "Submitted"

    })
    promise.catch(function(response){
        console.log("PUT request failed: ", response) 
        //you are logging the response but not handling it, catch is only for errors, you might can do a || "" to say do nothing if no error
        // indicate a failure, maybe why
    })
}

// Remove element function
function removeElement(elementId) {
    var element = document.getElementById(elementId);
    element.parentNode.removeChild(element);
}