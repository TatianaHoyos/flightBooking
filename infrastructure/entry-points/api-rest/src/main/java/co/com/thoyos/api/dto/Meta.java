package co.com.thoyos.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int page;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int recordsPerPage;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int totalRecords;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int totalPages;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String version;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long duration;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String message;

}
