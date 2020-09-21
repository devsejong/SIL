package net.chandol.study.book.rxjava.chapter02;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.subjects.PublishSubject;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


@Slf4j
public class SubjectSample {
    private final PublishSubject<Integer> subject = PublishSubject.create();

    public void numSubject() {
        new Thread(() -> {
            IntStream.range(0, 10).forEach(subject::onNext);
            subject.onCompleted();
        }).start();
    }

    public Observable<Integer> observe() {
        return subject;
    }

    public static void main(String[] args) {
        SubjectSample subjectSample = new SubjectSample();

        subjectSample.observe().subscribe(num->log.info("num={}", num));
        subjectSample.numSubject();
    }

}
