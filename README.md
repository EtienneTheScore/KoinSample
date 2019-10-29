# KoinSample

Sample app displaying how [Koin](https://github.com/InsertKoinIO/koin) works with [Scopes](https://insert-koin.io/docs/2.0/documentation/koin-core/scope-api.html) and [On Demand Dynamic Modules](https://developer.android.com/studio/projects/dynamic-delivery/on-demand-delivery).
<br>
You can login to a fake user. Once you're logged in, you can navigate to the user details.

### What's inside?

#### 2 screens
* Main: Shows Koin logs and some user interactions.
* User Details: Shows the username linked to the current session.

#### 3 modules
* app: default Android module containing the Main screen.
* session: Android Library module containing everything related to the session.
* userdetails: On Demand Dynamic Module containing the User Details screen.

#### 4 Koin modules
* appModule: `SplitInstallManager` dependency to manage Dynamic Modules.
* sessionModule: `SessionManager` and internal `FakeSessionGenerator` dependencies.
* mainModule: `MainViewModel` depency.
* userDetailsFeatureModule: `UserDetailsViewModel` dependency.

## Koin + Scopes

In this sample, `SessionManager` is a scoped dependency in the Session Scope.

### Session Scope

To create a scope, we need to choose a name and an ID. The scope's name is used to define scoped dependencies and its ID to retrieve those.
<br><br>
We also use an internal helper extension, so we don't have to deal with the scope's id and name elsewhere.

```kotlin
private const val SESSION_SCOPE_ID = "SESSION_ID"
internal const val SESSION_SCOPE_NAME = "SESSION_SCOPE_NAME"

@Synchronized
internal fun Koin.getSessionScope(): Scope {
    return getOrCreateScope(SESSION_SCOPE_ID, named(SESSION_SCOPE_NAME))
}
```

### Session Module
In this module, we define a SessionManaged as a scoped dependency.
<br>
When a `Session` is bound to this scope we return a `LoggedSessionManager`, otherwise an `UnloggedSessionManager` is returned.
<br>
By default, the session scope doesn't habe any `Session` bound to it.

```kotlin
val sessionModule = module {
    single { FakeSessionGenerator() }

    scope(named(SESSION_SCOPE_NAME)) {
        scoped {
            val session = getSessionScope().getOrNull<Session>()
            if (session == null) {
                UnloggedSessionManager(fakeSessionGenerator = get())
            } else {
                LoggedSessionManager(session)
            }
        }
    }
}
```

### Reload a Scope

When logging in, we need a new `SessionManager` with a `Session`.
We achieve that by closing the current Session Scope, then recreate it and declare the new `Session`.
<br>
Since this Session Scope has now a bounded `Session`, `SessionManager` will be an instance of `LoggedSessionManager`.

```kotlin
@Synchronized
internal fun Koin.reloadSessionScope(session: Session?) {
    getSessionScope().close()
    session?.let {
        getSessionScope().declare(it)
    }
}
```

### How to use

To make it easier to use the Session Scope, we can create an extension:

```kotlin
// Extension function so modules don't have to handle the session scope's name and id.
fun Scope.getSessionScope(): Scope {
    return getKoin().getSessionScope()
}
```

In this example, we inject `SessionManager` in `MainViewModel`.

```kotlin
val mainModule = module {
    viewModel { MainViewModel(sessionManager = getSessionScope().get()) }
}
```

## Koin + On Demand Dynamic Modules
TODO
