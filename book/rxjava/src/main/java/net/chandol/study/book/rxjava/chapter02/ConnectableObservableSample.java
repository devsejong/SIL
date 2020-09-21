package net.chandol.study.book.rxjava.chapter02;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


@Slf4j
public class ConnectableObservableSample {

    @SneakyThrows
    public static void main(String[] args) {
        Observable<Integer> numObservable = Observable.unsafeCreate(subscription -> {
            log.info("시작");
            IntStream.range(0, 10).forEach(subscription::onNext);

            log.info("종료");
            subscription.onCompleted();
        });

        ConnectableObservable<Integer> observable = numObservable.publish();

        observable.subscribe(new SampleObserver());
        observable.subscribe(new SampleObserver());

        observable.connect();
    }

    public static class SampleObserver implements Observer<Integer>{
        @Override
        public void onCompleted() {
            log.info("구독 종료");
        }

        @Override
        public void onError(Throwable e) {
            log.info("구독 중 error");
        }

        @Override
        public void onNext(Integer integer) {
            log.info("num = {}", integer);
        }
    }

}
