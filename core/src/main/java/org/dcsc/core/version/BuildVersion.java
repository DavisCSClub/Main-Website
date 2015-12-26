package org.dcsc.core.version;

import javax.persistence.*;

@Entity
@Table(name = "dcsc_builds", schema = "stats")
public class BuildVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checksum")
    private String checksum;

    @Column(name = "version_id")
    private int version;

    @Column(name = "production_build_id")
    private int productionBuildId;

    @Column(name = "preview_build_id")
    private int previewBuildId;

    @Column(name = "integration_build_id")
    private int integrationBuildId;

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getProductionBuildId() {
        return productionBuildId;
    }

    public void setProductionBuildId(int productionBuildId) {
        this.productionBuildId = productionBuildId;
    }

    public int getPreviewBuildId() {
        return previewBuildId;
    }

    public void setPreviewBuildId(int previewBuildId) {
        this.previewBuildId = previewBuildId;
    }

    public int getIntegrationBuildId() {
        return integrationBuildId;
    }

    public void setIntegrationBuildId(int integrationBuildId) {
        this.integrationBuildId = integrationBuildId;
    }

    public String getBuildVersion() {
        return String.format("%s.%s", version, productionBuildId);
    }
}
