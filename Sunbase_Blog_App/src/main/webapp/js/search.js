
//Serach functionality
function searchBlogs() {
    const searchType = document.getElementById("searchType").value;
    const searchInput = document.getElementById("searchInput").value;

    if (searchInput.trim() === "") {
        alert("Please enter a search term.");
        return;
    }

    if (searchType === "date") {
        const fullDatePattern = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/; // Full date format
        const partialDatePatterns = [
            /^\d{4}$/, // Only year
            /^\d{4}-\d{2}$/, // Year and month
            /^\d{4}-\d{2}-\d{2}$/, // Year, month, and day
            /^\d{2}-\d{2}$/, // Month and day
            /^\d{2}$/, // Only month or only day
            /^\d{2}:\d{2}:\d{2}$/, // Only time
            /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/, // Year, month, day, and hours and minutes
            /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/, // Year, month, day, hours, minutes, and seconds
            /^\d{4} \d{2}:\d{2}:\d{2}$/, // Year and time
            /^\d{4}-\d{2} \d{2}:\d{2}:\d{2}$/, // Year, month, and time
            /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/, // Year, month, day, and time (without seconds)
        ];

        const isValidDate = fullDatePattern.test(searchInput) || partialDatePatterns.some(pattern => pattern.test(searchInput));

        if (!isValidDate) {
            alert("Please enter a valid date format (yyyy-mm-dd hh:mm:ss) or a partial format (yyyy, yyyy-mm, yyyy-mm-dd, mm-dd, mm, dd, hh:mm:ss).");
            return;
        }
    }

    const form = document.createElement("form");
    form.method = "GET";
    form.action = "SearchServlet";

    const typeInput = document.createElement("input");
    typeInput.type = "hidden";
    typeInput.name = "searchType";
    typeInput.value = searchType;

    const queryInput = document.createElement("input");
    queryInput.type = "hidden";
    queryInput.name = "query";
    queryInput.value = searchInput;

    form.appendChild(typeInput);
    form.appendChild(queryInput);
    document.body.appendChild(form);
    form.submit();
}



//delete functionality
function confirmDelete(blogId) {
    if (confirm("Are you sure you want to delete this blog?")) {
        window.location.href = "DeleteBlogServlet?id=" + blogId;
    }
}


