package net.mattcarpenter.benkyou.srsservice.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateLayoutRequest {
    private String frontHtml;
    private String backHtml;
    private String frontCss;
    private String backCss;
    private String name;

    private List<LayoutFieldModel> fields;
}
