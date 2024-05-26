@import exercise.dto.users.UsersPage
@param UsersPage page

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
        <ul class="list-group">
        @for(var user : page.getUsers())
            <li class="list-group-item"><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
            <a href="${user.getEmail()}">${user.getEmail()}</a></li>
        @endfor
        </ul>
        <a class="btn btn-primary" href="/users/build" role="button">New User</a>
        </div>
    `
)