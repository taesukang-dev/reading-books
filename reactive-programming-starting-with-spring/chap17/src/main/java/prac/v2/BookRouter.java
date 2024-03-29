package prac.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV2")
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBookV2(BookHandler handler) {
        return route()
                .POST("/v2/books", handler::createBook)
                .GET("/v2/books", handler::getBooks)
                .GET("/v2/books/{book-id}", handler::getBook)
                .PATCH("/v2/books/{book-id}", handler::patchBook)
                .build();
//        return route(POST("/v1/books"), handler::createBook)
//                .andRoute(GET("/v1/books/{book-id}"), handler::getBook)
//                .andRoute(PATCH("/v1/books/{book-id}"), handler::patchBook)
//                .andRoute(GET("/v1/books"), handler::getBooks);
    }
}
