import { filter, OperatorFunction } from 'rxjs'

export function valueIfPresentOrError<T> (value: T | undefined | null): T {
  if (value === null || value === undefined) {
    throw Error('Expected value to be present.')
  }
  return value
}

export function filterUndefinedOrNull<T> (): OperatorFunction<T | null | undefined, T> {
  return filter(value => value != null) as OperatorFunction<T | null | undefined, T>
}

export function filterUndefinedOrNullForCombinedValues<T, R> (): OperatorFunction<[T | null | undefined, R | null | undefined], [T, R]> {
  return filter((values): values is [T, R] => {
    return values[0] !== undefined && values[1] !== undefined
  })
}
