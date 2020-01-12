package net.mattcarpenter.benkyou.srsservice.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {
    private UUID id;
    private UUID createdBy;
    private JsonNode data;
}
