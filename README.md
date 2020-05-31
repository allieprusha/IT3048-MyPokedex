# Design Document: My Pokedex

Allie Prusha

Tristan Palmer

Michael Lawson

## Introduction

Have you ever wanted your very own Pokédex? Do you often see strange species of animals that could be Pokémon? Then this application is for you. Download it now to use features such as:

- Search through various known Pokémon.
- Take pictures of Pokémon encounter locations and save them to your user created account.
- Look through previous saved Pokémon encounter locations using your created account.


## Storyboard (Screen Mockups)

[https://projects.invisionapp.com/prototype/MyPokedex-Storyboard-ckamkvt69004wqu01al3jrpvm/play/c030acf7](https://projects.invisionapp.com/prototype/MyPokedex-Storyboard-ckamkvt69004wqu01al3jrpvm/play/c030acf7)

 ![](RackMultipart20200531-4-1m3koc7_html_a134b96fdecb3544.png)

## Functional Requirements

- **Requirement 1: Details for Pokemon**
  - **Scenario:** As a user, I want to be able to see the details related to each Pokémon I press.
  - **Dependencies:** Pokémon data is readily available and loaded into the mobile application.
  - **Assumptions:**
    - Pokémon are sorted into a clickable list.
    - Data associated with each Pokémon is correctly matched with its Pokémon.
  - **Examples**
    - **Given** a list of Pokémon, **when** I click on Pikachu, **then** I should see one of the following:
      1. Moves: mega-punch
      2. Type: electric
    - **Given** a list of Pokémon, **when** I click on Charizard, **then** I should see one of the following:
      1. Moves: fire-punch
      2. Type: fire
    - **Given** a list of Pokémon, **when** I do not click on a Pokémon, **then** nothing should happen.
- **Requirement 2: Search Pokemon**
  - **Scenario:** As a user, when I search for a Pokémon, I want to see the Pokémon I search for in the list.
  - **Dependencies:** Pokémon data is readily available to search through.
  - **Assumptions:**
    - Auto-completion is enabled to help the user find their searched Pokémon.
    - Users can press on searched Pokémon (see **requirement 1** ).
  - **Examples**
    - **Given** data is readily available, **when** I search for Pikachu, **then** I should see an image of Pikachu that I can press (see **requirement 1** ).
    - **Given** data is readily available, **when** I search for Charizard, **then** I should see an image of Charizard that I can press (see **requirement 1** ).
    - **Given** data is readily available, **when** I search for &quot;xyz&quot; **then** I should see no image pop-ups.
- **Requirement 3: Sign on and Upload Encounters**
  - **Scenario:** As a user, I want to be able to sign-in and upload my own Pokémon sighting locations.
  - **Dependencies**
    - Camera access is needed.
    - Firebase sign-in needed.
  - **Assumptions**
    - Users can sign-in using a Google account.
    - User&#39;s phone has a working camera.
  - **Examples**
    - **Given** the user has signed-in and has granted access to the camera, **when** the user takes a photo and presses the save button, **then** the photo and associated data gets saved for the user to access late (see **requirement 4** ).
    - **Given** the user has signed-in but has not granted access to the camera, **when** the user wants to take a photo, **then** a pop-up will be displayed asking the user to grant camera access.
    - **Given** the user has not signed-in, **when** the user wants to take a photo, **then** access to the camera will be disabled.
- **Requirement 4: View Saved Encounters**
  - **Scenario:** As a user, I want to be able to look through saved Pokémon encounters.
  - **Dependencies:** Firebase sign-in needed.
  - **Assumptions**
    - Users have saved Pokémon encounters in the past.
    - Users have signed-in using a Google account.
  - **Example**
    - **Given** the user has signed-in and has taken a photo of Pokémon encounter locations, **when** the user presses the &quot;file&quot; button, **then** a list of photos and associated information such as Pokémon type, description, and name will be presented in a list format.
    - **Given** the user has signed-in and has not taken photos of Pokémon encounter locations, **when** the user presses the &quot;file&quot; button, **then** a blank list will be presented.
    - **Given** the user has not signed-in and has not taken photos of Pokémon encounter locations, **when** the user wants to enable this feature, **then** the user must sign-in using their Google account.

## Class Diagram (Show data classes (DTOs), Activities, Fragments, MVVM, etc.)

  - Note from ya girl - I named our package &quot;edu.uc.it3048.mypokedex&quot;

## Class Diagram Description

## Scrum Roles

- DevOps/Product Owner/Scrum Master: Allie Prusha
- Frontend Developer: Tristan Palmer
- Integration Developer: Michael Lawson

## Weekly Discord Meetings

- Saturday Evenings at 6PM on Discord
- Use this link to our discord channel: https://discord.gg/y35YJA
