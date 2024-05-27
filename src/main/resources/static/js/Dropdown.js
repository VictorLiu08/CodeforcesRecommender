document.addEventListener('DOMContentLoaded', function() {
    const tagsContainer = document.getElementById('tags');
    const dropdownToggle = document.querySelector('.dropdown-toggle');
    const dropdownMenu = document.querySelector('.dropdown-menu');
    const placeholderText = 'Choose Tags';
    let hasTags = false;

    function updatePlaceholder() {
        if (!hasTags) {
            tagsContainer.textContent = placeholderText;
        } else if (tagsContainer.textContent === placeholderText) {
            tagsContainer.textContent = '';
        }
    }

    updatePlaceholder();

    dropdownToggle.addEventListener('click', function(event) {
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
        event.stopPropagation();
    });

    // Close the dropdown when clicking outside of it
    window.addEventListener('click', function() {
        dropdownMenu.style.display = 'none';
    });

    // Stop propagation on dropdown menu to prevent it from closing when clicking inside
    dropdownMenu.addEventListener('click', function(event) {
        event.stopPropagation();
    });

    const dropdownItems = document.querySelectorAll('.dropdown-menu input[type="checkbox"]');
    dropdownItems.forEach(item => {
        item.addEventListener('change', function() {
            if (this.checked) {
                if (!hasTags) {
                    hasTags = true;
                    updatePlaceholder();
                }
                const tag = createTag(this.id, this.value);
                tagsContainer.appendChild(tag);
            } else {
                const tagToRemove = tagsContainer.querySelector(`[data-id="${this.id}"]`);
                tagsContainer.removeChild(tagToRemove);
                if (tagsContainer.children.length === 0) {
                    hasTags = false;
                    updatePlaceholder();
                }
            }
        });
    });

    function createTag(id, value) {
        const span = document.createElement('span');
        span.classList.add('selected-tag');
        span.textContent = value;
        span.setAttribute('data-id', id);

        const removeBtn = document.createElement('span');
        removeBtn.classList.add('remove-tag');
        removeBtn.innerHTML = '&times;';
        removeBtn.onclick = function(event) {
            event.stopPropagation(); // Prevent the dropdown from toggling when removing a tag
            const checkbox = document.querySelector(`#${id}`);
            checkbox.checked = false;
            span.parentNode.removeChild(span);
            if (tagsContainer.children.length === 0) {
                hasTags = false;
                updatePlaceholder();
            }
        };

        span.appendChild(removeBtn);
        return span;
    }
});
