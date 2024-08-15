function addAddressInfoRow() {
    const container = document.getElementById('addressInfoContainer');
    const rowIndex = container ? container.children.length : 0;
    const newAddress = document.createElement('div');

    newAddress.innerHTML = `
        <input type="text"
        class="form-control me-2"
        name="addresses[${rowIndex}].value"
        placeholder="Address">
        <input type="checkbox"
            class="form-check-input me-1"
            name="addresses[${rowIndex}].isPrimary">
        <label class="form-check-label">Primary?</label>
    `;
    container.appendChild(newAddress);
}

function addContactInfoRow() {
    const container = document.getElementById('contactInfoContainer');
    const rowIndex = container ? container.children.length : 0;
    const newContact = document.createElement('div');
    newContact.className = 'mb-3 d-flex align-items-center';
    newContact.innerHTML = `
        <input type="text" class="form-control me-2" name="contacts[${rowIndex}].contactDetails" placeholder="Contact">
        <input type="checkbox" class="form-check-input me-1" name="contacts[${rowIndex}].primary">
        <label class="form-check-label">Primary?</label>
    `;
    container.appendChild(newContact);
}
