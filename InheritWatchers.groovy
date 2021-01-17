/*
    Script written to automatically inherit parent issue watchers on sub-task in its creation stage
    Script is called in sub-task creation stage postfunction
*/
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.user.ApplicationUser

// Check if issue is Sub-task
if(!issue.isSubTask()){
    return
}

def watcherManager = ComponentAccessor.getWatcherManager()
def parent = issue.getParentObject()

// Check if it has a parent
if(parent == null){
    return
}

def user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
def locale = ComponentAccessor.getLocaleManager().getLocaleFor(user)
def watchers = watcherManager.getWatchers(parent, locale)

watchers.each {watcher ->;
    watcherManager.startWatching(watcher, issue)
}
/*
    Known mistakes and issues:
        1) Add script call after sub-task issue creation stage in postfunction
*/