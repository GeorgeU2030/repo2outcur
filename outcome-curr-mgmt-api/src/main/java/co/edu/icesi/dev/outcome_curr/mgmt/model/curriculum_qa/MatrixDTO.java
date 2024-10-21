package co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa;

import lombok.Builder;

import java.util.List;
@Builder
public record MatrixDTO (List<RowDTO> matrix) { }
