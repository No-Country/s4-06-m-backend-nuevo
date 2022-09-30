package ecommerce.eco.config.filters;

import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.request.ProductFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSpecifications {
    public Specification<Product> getFiltered(ProductFilterRequest productFilters) {

        // Lambda Function:
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // == Title ==
            if (StringUtils.hasLength(productFilters.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + productFilters.getTitle().toLowerCase() + "%"
                        )
                );
            }

            // == categoria ==
   /*         if (!CollectionUtils.isEmpty(movieFilters.getGenre()) ){
                Join<Movie, Gender> join = root.join("genders", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicates.add(genresId.in(movieFilters.getGenre()));
            }*/

            query.distinct(true);

            // == Order ==
            String orderByField = "title";
            query.orderBy(
                    productFilters.isASC()
                            ? criteriaBuilder.asc(root.get(orderByField))
                            : criteriaBuilder.desc(root.get(orderByField))
            );

            // MAIN RETURN:
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
