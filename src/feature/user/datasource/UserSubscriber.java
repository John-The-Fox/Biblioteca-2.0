package feature.user.datasource;

import feature.book.datasource.BookListener;

public interface UserSubscriber {
    void subscribe(UserListener userObserver);
}
