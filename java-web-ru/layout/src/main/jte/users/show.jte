@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    header = @`
    <header class="d-flex justify-content-center py-3">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="/" class="nav-link" aria-current="page">Home</a></li>
            <li class="nav-item"><a href="/users" class="nav-link active">Users</a></li>
        </ul>
    </header>
    `,
    content = @`
        <div class="px-4 py-5 my-5 text-center">
        <p class="display-5 fw-bold">${page.getUser().getFirstName()} ${page.getUser().getLastName()} <a href="${page.getUser().getEmail()}">${page.getUser().getEmail()}</a></p>
        </div>
    `
)