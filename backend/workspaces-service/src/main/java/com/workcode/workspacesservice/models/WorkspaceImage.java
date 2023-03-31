package com.workcode.workspacesservice.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.workcode.workspacesservice.enums.EDevLanguage;
import com.workcode.workspacesservice.enums.EWorkspaceImage;

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
