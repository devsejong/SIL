package net.chandol.study.book.rxjava.chpater04;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.schedulers.Schedulers;

@Slf4j
public class DeferTest {
    public static void main(String[] args) throws InterruptedException {
        log.info("start ts : " + System.currentTimeMillis());

        // defer가 존재하기 때문에 Observable은 subscribe가 되는경우에 만들어집니다.
        Observable.defer(() -> Observable.just(getHeavyData()))
                .subscribeOn(Schedulers.io())
                .subscribe(resp -> log.info(resp),
                        thr -> log.info(thr.getMessage()),
                        () -> log.info("Completed!!"));

        log.info("end ts : " + System.currentTimeMillis());

        Thread.sleep(2000);
    }

    private static String getHeavyData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Just Item";
    }
}
