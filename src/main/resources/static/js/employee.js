function addAddressInfoRow() {
    const container = document.getElementById('updateEmployee_addressInfoContainer');
    const addressIndex = document.querySelectorAll('#updateEmployee_addressInfoContainer input[type="text"]').length;
    const newInputGroup = document.createElement('div');
    newInputGroup.className = 'form__update-employee__group mt-2';

    const newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = `addresses[${addressIndex}].addressDetails`;
    newInput.className = 'form__update-employee__input form-control';
    newInput.placeholder = 'Address Details';

    const newCheckbox = document.createElement('input');
    newCheckbox.type = 'checkbox';
    newCheckbox.name = `addresses[${addressIndex}].isPrimary`;

    const newLabel = document.createElement('label');
    newLabel.innerHTML = 'Primary?';
    newLabel.className = 'form-check-label ms-2';

    newInputGroup.appendChild(newInput);
    newInputGroup.appendChild(newCheckbox);
    newInputGroup.appendChild(newLabel);

    container.appendChild(newInputGroup);
}

function addContactInfoRow() {
    const container = document.getElementById('updateEmployee_contactInfoContainer');
    const contactIndex = document.querySelectorAll('#updateEmployee_contactInfoContainer input[type="text"]').length;
    const newInputGroup = document.createElement('div');
    newInputGroup.className = 'form__update-employee__group mt-2';

    const newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = `contacts[${contactIndex}].contactDetails`;
    newInput.className = 'form__update-employee__input form-control';
    newInput.placeholder = 'Contact Details';

    const newCheckbox = document.createElement('input');
    newCheckbox.type = 'checkbox';
    newCheckbox.name = `contacts[${contactIndex}].isPrimary`;

    const newLabel = document.createElement('label');
    newLabel.innerHTML = 'Primary?';
    newLabel.className = 'form-check-label ms-2';

    newInputGroup.appendChild(newInput);
    newInputGroup.appendChild(newCheckbox);
    newInputGroup.appendChild(newLabel);

    container.appendChild(newInputGroup);
}
