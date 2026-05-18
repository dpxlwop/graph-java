PROJECT_NAME = s21algo

all: clean s21_algo

s21_algo: buildLibs
	./gradlew :graphAlgorithms:jar

buildLibs: s21_containers s21_graph
	mv out/s21_containers.jar graphAlgorithms/libs/
	mv out/s21_graph.jar graphAlgorithms/libs/

s21_containers:
	./gradlew :containers:jar

s21_graph:
	./gradlew :graph:jar

test:
	./gradlew test

test_graph:
	./gradlew :graph:test

test_containers:
	./gradlew :containers:test

test_algo:
	./gradlew :graphAlgorithms:test

rebuild: clean all

clean:
	./gradlew clean
	rm -rf out
	rm -rf graphAlgorithms/libs/*

.PHONY: \
	all \
	s21_algo \
	buildLibs \
	s21_containers \
	s21_graph \
	test \
	test_graph \
	test_containers \
	test_algo \
	rebuild \
	clean