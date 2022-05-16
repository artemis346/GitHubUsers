[imageDetails]: ./screens/screenDetails.png "Screen Details"
[imageList]: ./screens/screenList.png "Screen List"

GitHub Users
============

This is the sample project for trying new android technologies and frameworks and architectural approaches.
App let user to search users on GitHub with [GitHub Search API](https://docs.github.com/en/rest/reference/search#search-users) and show the additional information about the user with the [GitHub Get User API](https://docs.github.com/en/rest/users/users#get-a-user).
The app consist of two feature screen: Search Screen and Details Screen

| Search Screen            |        Details Screen            |
|--------------------------|----------------------------------|
|![Screen List][imageList] |  ![Screen Details][imageDetails] |


Technology stack
----------------
✅ MVVM

✅ Coroutines with Flow

✅ JetPack Compose

✅ JetPack Navigation

✅ Hilt

✅ Retrofit

✅ GSON

✅ Paging3

✅ Multimodule architecture

✅ Mockito

For developing the application was chosen the stack that let easily add new features to the project from the one side and avoid creating additional classes in the developing process from the other side.

Using the ***JetPack Compose*** let to avoid of handling RecyclerViews and creating adapters and ViewHolder instances in project and listen to scrolling state of the list.

Using ***Hilt*** over the Dagger 2 let to easily inject modules in compose screens.

With ***Mutlimodule architecture*** and dependency injection based on ***Hilt*** very easy to add new features in the project, and cover all layers with the test. 

All dependencies of the project are standard stack for modern android application, so new developer that would join the project will be able to quickly start creating new feature without additional landing on technological stack. 