package ecommerce.eco.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {
    private  Long id;
    private String fileUrl;
    private String name;
}
