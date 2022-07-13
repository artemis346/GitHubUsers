[imageDetails]: ./screens/screenDetails.png "Screen Details"
[imageList]: ./screens/screenList.png "Screen List"

GitHub Users
============

This is the sample project for trying new Android technologies and frameworks and architectural approaches. 
App shows the GitHub user list with [GitHub List User API](https://docs.github.com/en/rest/users/users#list-users) and shows additional information about the user with the [GitHub Get User API](https://docs.github.com/en/rest/users/users#get-a-user). 
The app consists of two feature screens: List Screen and Details Screen

| Search Screen            |        Details Screen            |
|--------------------------|----------------------------------|
|![Screen List][imageList] |  ![Screen Details][imageDetails] |


Technology stack
----------------
✅ Clean with MVVM

✅ Coroutines with Flow

✅ JetPack Compose

✅ JetPack Navigation

✅ Hilt

✅ Retrofit

✅ GSON

✅ Multimodule architecture

✅ Mockito

✅ Room

Such a stack was chosen to easily add new features to the project from the one side and to avoid creating additional classes in the developing process from the other side.

Using the ***JetPack*** Compose lets avoid handling RecyclerViews and creating adapters and ViewHolder instances in the project and listen to the scrolling state of the list.

Using ***Hilt*** over the Dagger 2 lets easily inject modules in compose screens.

With ***Mutlimodule architecture*** and dependency injection based on Hilt it is very easy to add new features in the project, and cover all layers with the test. 

All dependencies of the project are standard stack for modern Android applications, so new developers that would join the project will be able to quickly start creating new features without additional landing on technological stack.

Retrofit, GSON were chosen as reliable frameworks to organise network layer.

***Room*** database is used for caching the list of users.

The "userdetails" feature with related UseCases and Repositories is covered by unit tests just for an example.

Modules structure
-----------------
- ```app``` - The main module of application with the instance of App and Main Activity
- ```domain``` - The module that contains UseCases of the application and other domain entities of the application
- ```features``` - Package that contains feature modules of the application
  - ```userlist``` - Module for features of showing list of GitHub users 
  - ```userlist-api``` - Module for navigation interface for ```userlist``` feature
  - ```userdetails``` - Module for feature of showing the detail information about user
  - ```userdatails-api``` - Module for navigation interface for ```userdetails``` feature
- ```navigation``` - Module for base classes to organize navigation in application
- ```network``` - Module with logic of Network layer
- ```repository``` - Module with the interfaces of repositories
- ```repository-impl``` - Module with the implementation of repositories interfaces
- ```uikit``` - Module for base UI components, styles, colors and string
- ```utils``` - Module for base utils methods, extensions and classes 