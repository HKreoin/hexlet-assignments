@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
  content = @`
    <main role="main" class="container">
      <form action="/users" method="get">
        <input type="search" required name="term" value="${page.getTerm()}" >
        <input type="submit" value="Искать">
      </form>

      <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
        <div class="my-3 p-3 bg-white rounded shadow-sm">
          @if(page.getUsers().isEmpty())
           <div class="media text-muted pt-3">
             <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">Пока не добавлено ни одного пользователя</p>
           </div>
          @else
           @for(var user : page.getUsers())
             <div class="media text-muted pt-3">
               <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#6f42c1"></rect><text x="50%" y="50%" fill="#6f42c1" dy=".3em">32x32</text></svg>
               <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <strong class="d-block text-gray-dark"><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></strong>
                ${user.getEmail()}
               </p>
             </div>
           @endfor
          @endif
        </div>
      </div>
    </main>
  `
)
