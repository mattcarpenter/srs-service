package net.mattcarpenter.benkyou.srsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLayoutRequest {
    private String frontHtml;
    private String backHtml;
    private String frontCss;
    private String backCss;
    private String name;

    private List<LayoutFieldModel> fields;
}
