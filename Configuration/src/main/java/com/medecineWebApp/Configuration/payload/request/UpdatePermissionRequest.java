package com.medecineWebApp.Configuration.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePermissionRequest {
    private Long id;
    private String module;
    private boolean canRead;
    private boolean canWrite;
    private boolean canCreate;
    private boolean canDelete;
    private boolean canImport;
    private boolean canExport;
    private boolean canApprove;
    private boolean canValidate;
    private boolean canAssign;
    private boolean canGenerateReport;
    private boolean canActivate;
}
