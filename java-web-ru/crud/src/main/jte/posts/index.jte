@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage

@param PostsPage page

@template.layout.page(
    content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <a href="${NamedRoutes.postsPath()}">Все посты</a>
        </main>
    </div>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}/?page=${page.getPageNumber()-1}">Previous</a></li>
            <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}/?page=${page.getPageNumber()+1}">Next</a></li>
        </ul>
    </nav>
    <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
        <div class="my-3 p-3 bg-white rounded shadow-sm">
            
            @if(page.getPosts().isEmpty())
                <div class="media text-muted pt-3">
                    <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">Пост не найден</p>
                </div>
            @else
                @for(var post : page.getPosts())
                    <div class="media text-muted pt-3">
                        <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#6f42c1"></rect><text x="50%" y="50%" fill="#6f42c1" dy=".3em">32x32</text></svg>
                        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                            <strong class="d-block text-gray-dark"><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></strong>
                            ${post.getBody()}
                        </p>
                    </div>
                @endfor
            @endif
        </div>
      </div>
    `
)
