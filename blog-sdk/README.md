# SDK的API说明

### 配置文件说明

- **例如blog-web项目依赖了blog-sdk模块，如果在blog-web项目的class跟目录下定义blog-sdk.properties资源文件，那么blog-sdk模块会加载该资源文件获取所需的值，如果没有定义，则默认加载blog-sdk模块下的blog-sdk.properties资源文件**

- **默认参数列表**

  ```
  #文章排名列表接口路径
  articleRankUrl=http://localhost:8083/cache/article/rank
  ```

  ​

### 获取文章排名数据

```java
import com.zuoxiaolong.blog.model.dto.cache.ArticleRankResponseDto;
import java.util.List;
public class BlogInterfaceHandlerTest {
    public static void main(String[] args) throws Exception {
        List<ArticleRankResponseDto> list = BlogInterfaceHandler.getArticleRank();
    }
}
```

​






