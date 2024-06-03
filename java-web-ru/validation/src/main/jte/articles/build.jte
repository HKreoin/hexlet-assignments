@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
    content = @`
  @if(page.getErrors() != null)
      <ul>
          @for(var validator : page.getErrors().values())
              @for(var error : validator)
                  <li>${error.getMessage()}</li>
              @endfor
          @endfor
      </ul>
  @endif

  <form action="/articles" method="post">
    <div>
      <label>
        Имя
        <input type="text" name="title" placeholder="Введите название статьи" value="${page.getTitle()}" />
      </label>
    </div>
    <div>
      <label for="story">Tell us your story:</label>
    </div>
    <div>
      <textarea id="story" name="content" placeholder="Введите текст" rows="5" cols="33">${page.getContent()}</textarea>
    </div>
    <input type="submit" value="Опубликовать" />
  </form>
  `
)
