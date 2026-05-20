PROJECT_NAME = s21algo

all: clean s21_cli

run: s21_cli
	java -jar out/s21_cli.jar

s21_cli: clean s21_algorithms
	cp out/s21_graph.jar cli/libs
	cp out/s21_graphAlgorithms.jar cli/libs
	./gradlew :cli:jar

s21_algorithms: buildLibs
	./gradlew :graphAlgorithms:jar

buildLibs: s21_containers s21_graph
	cp out/s21_containers.jar graphAlgorithms/libs/
	cp out/s21_graph.jar graphAlgorithms/libs/

s21_containers:
	./gradlew :containers:jar

s21_graph:
	./gradlew :graph:jar

test: test_algo test_graph test_containers

test_graph:
	./gradlew :graph:test --rerun-tasks

test_containers:
	./gradlew :containers:test --rerun-tasks

test_algo:
	./gradlew :graphAlgorithms:test --rerun-tasks

rebuild: clean all

clean:
	./gradlew clean
	rm -rf out
	rm -rf graphAlgorithms/libs/*
	rm -rf cli/libs/*

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