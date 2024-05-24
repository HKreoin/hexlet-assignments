@import exercise.dto.users.UserPage
@param UserPage page

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
        <title>User</title>
    </head>
    <body>
        <h1>User</h1>
            <div>
                <h2>${page.getUser().getFirstName()}</h2>
                <p>${page.getUser().getLastName()}</p>
                <p>${page.getUser().getEmail()}</p>
            </div>
    </body>
</html>
