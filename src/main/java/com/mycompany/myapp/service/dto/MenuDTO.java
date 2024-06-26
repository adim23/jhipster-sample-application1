package com.mycompany.myapp.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Menu} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MenuDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private Boolean enabled;

    private String icon;

    private String uri;

    private String defaultImage;

    @Lob
    private byte[] defaultImageData;

    private String defaultImageDataContentType;

    private LocalDate createdDate;

    private UserDTO createdBy;

    private WebPageDTO page;

    private MenuDTO parent;

    private TourCategoryDTO tourCategory;

    private DestinationDTO destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public byte[] getDefaultImageData() {
        return defaultImageData;
    }

    public void setDefaultImageData(byte[] defaultImageData) {
        this.defaultImageData = defaultImageData;
    }

    public String getDefaultImageDataContentType() {
        return defaultImageDataContentType;
    }

    public void setDefaultImageDataContentType(String defaultImageDataContentType) {
        this.defaultImageDataContentType = defaultImageDataContentType;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public WebPageDTO getPage() {
        return page;
    }

    public void setPage(WebPageDTO page) {
        this.page = page;
    }

    public MenuDTO getParent() {
        return parent;
    }

    public void setParent(MenuDTO parent) {
        this.parent = parent;
    }

    public TourCategoryDTO getTourCategory() {
        return tourCategory;
    }

    public void setTourCategory(TourCategoryDTO tourCategory) {
        this.tourCategory = tourCategory;
    }

    public DestinationDTO getDestination() {
        return destination;
    }

    public void setDestination(DestinationDTO destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuDTO)) {
            return false;
        }

        MenuDTO menuDTO = (MenuDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, menuDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", enabled='" + getEnabled() + "'" +
            ", icon='" + getIcon() + "'" +
            ", uri='" + getUri() + "'" +
            ", defaultImage='" + getDefaultImage() + "'" +
            ", defaultImageData='" + getDefaultImageData() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", page=" + getPage() +
            ", parent=" + getParent() +
            ", tourCategory=" + getTourCategory() +
            ", destination=" + getDestination() +
            "}";
    }
}
