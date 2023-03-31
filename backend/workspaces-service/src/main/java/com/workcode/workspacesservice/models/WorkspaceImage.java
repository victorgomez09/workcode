package com.workcode.workspacesservice.models;

import java.util.Collection;
import java.util.List;

import com.workcode.workspacesservice.enums.EDevLanguage;
import com.workcode.workspacesservice.enums.EWorkspaceImage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workspace_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EWorkspaceImage image;

    @ElementCollection(targetClass = EDevLanguage.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "development_languages")
    private Collection<EDevLanguage> languages;
    
    @OneToMany(mappedBy = "image", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Workspace> workspaces;
}
